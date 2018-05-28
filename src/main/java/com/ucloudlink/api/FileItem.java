package com.ucloudlink.api;

import java.io.*;

/**
 * Created by jiang.zheng on 2017/2/22.
 */
public class FileItem {

    private String fileName;
    private String mimeType;
    private byte[] content;
    private File file;

    /**
     * 基于本地文件
     * @param file
     */
    public FileItem(File file) {
        this.file = file;
    }

    /**
     * 基于绝对路径
     * @param filePath
     */
    public FileItem(String filePath) {
        this(new File(filePath));
    }

    /**
     * 基于文件名和字节流
     * @param fileName
     * @param content
     */
    public FileItem(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    /**
     * 基于文件名、字节流和媒体类型
     * @param fileName
     * @param mimeType
     * @param content
     */
    public FileItem(String fileName, String mimeType, byte[] content) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        this.content = content;
    }

    public String getFileName() {
        if (this.fileName == null && this.file != null && this.file.exists()) {
            this.fileName = file.getName();
        }
        return this.fileName;
    }

    public String getMimeType() throws IOException {
        if (this.mimeType == null) {
            this.mimeType = UcloudlinkUtils.getMimeType(getContent());
        }
        return mimeType;
    }

    public byte[] getContent() throws IOException {
        if (this.content == null && this.file != null && this.file.exists()) {
            InputStream in = null;
            ByteArrayOutputStream out = null;

            try {
                in = new FileInputStream(this.file);
                out = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read()) != -1) {
                    out.write(ch);
                }
                this.content = out.toByteArray();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }
        return this.content;
    }
}
