package kr.co.bit.vo;

public class FileVO {

    private int no;
    private String filePath;
    private String orgName;
    private String saveName;
    private long fileSize;
    private String title;
    private int user_no;


    public FileVO() {
    }

    public FileVO(int no, String filePath, String orgName, String saveName, long fileSize, String title, int user_no) {
        this.no = no;
        this.filePath = filePath;
        this.orgName = orgName;
        this.saveName = saveName;
        this.fileSize = fileSize;
        this.title = title;
        this.user_no = user_no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    @Override
    public String toString() {
        return "FileVO{" +
                "no=" + no +
                ", filePath='" + filePath + '\'' +
                ", orgName='" + orgName + '\'' +
                ", saveName='" + saveName + '\'' +
                ", fileSize=" + fileSize +
                ", title='" + title + '\'' +
                ", user_no=" + user_no +
                '}';
    }
}
