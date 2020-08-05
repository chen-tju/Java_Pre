import de.innosystec.unrar.Archive;
import de.innosystec.unrar.UnrarCallback;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class RarDecompressionUtil {

    private static final String SEPARATOR = File.separator;


    /**
     * 解压指定RAR文件到当前文件夹
     */
    public static void unrar(String srcRar, String password) throws IOException {
        unrar(srcRar, null, password);
    }

    /**
     * 解压指定的RAR压缩文件到指定的目录中
     */
    public static void unrar(String srcRar, String destPath, String password) throws IOException {
        File srcFile = new File(srcRar);
        if (!srcFile.exists()) {
            return;
        }
        if (null == destPath || destPath.length() == 0) {
            unrar(srcFile, srcFile.getParent(), password);
            return;
        }
        unrar(srcFile, destPath, password);
    }

    /**
     * 解压指定RAR文件到当前文件夹
     */
    public static void unrar(File srcRarFile, String password) throws IOException {
        if (null == srcRarFile || !srcRarFile.exists()) {
            throw new IOException("文件不存在.");
        }
        unrar(srcRarFile, srcRarFile.getParent(), password);
    }

    /**
     * 解压指定RAR文件到指定的路径
     */
    public static void unrar(File srcRarFile, String destPath, String password) throws IOException {
        if (null == srcRarFile || !srcRarFile.exists()) {
            throw new IOException("压缩文件不存在.");
        }
        if (!destPath.endsWith(SEPARATOR)) {
            destPath += SEPARATOR;
        }
        Archive archive;
        OutputStream unOut = null;
        try {
            archive = new Archive(srcRarFile, new UnrarCallback() {
                @Override
                public boolean isNextVolumeReady(File nextVolume) {
                    return false;
                }

                @Override
                public void volumeProgressChanged(long current, long total) {
                }
            }, password, false);
            FileHeader fileHeader = archive.nextFileHeader();
            while (null != fileHeader) {
                if (!fileHeader.isDirectory()) {
                    // 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
                    String destFileName;
                    String destDirName;
                    if (SEPARATOR.equals("/")) {
                        // 非windows系统
                        destFileName = getDestFileNameString(destPath, fileHeader);
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
                    } else {
                        // windows系统
                        destFileName = getDestFileNameString(destPath, fileHeader);
                        destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
                    }
                    // 2创建文件夹
                    File dir = new File(destDirName);
                    if (!dir.exists() || !dir.isDirectory()) {
                        dir.mkdirs();
                    }
                    // 抽取压缩文件
                    unOut = new FileOutputStream(new File(destFileName));
                    archive.extractFile(fileHeader, unOut);
                    unOut.flush();
                    unOut.close();
                }
                fileHeader = archive.nextFileHeader();
            }
            archive.close();
        } catch (RarException e) {
            e.printStackTrace();
        } finally {
            if (unOut != null) {
                unOut.close();
            }
        }
    }

    private static String getDestFileNameString(String destPath, FileHeader fileHeader) {
        String destFileName;
        String fileNameW = fileHeader.getFileNameW();
        if (null == fileNameW || "".equals(fileNameW)) {
            destFileName = (destPath + fileHeader.getFileNameString()).replaceAll("/", "\\\\");
        } else {
            destFileName = (destPath + fileHeader.getFileNameW()).replaceAll("/", "\\\\");
        }
        return destFileName;
    }
}
