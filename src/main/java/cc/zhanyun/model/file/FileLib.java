package cc.zhanyun.model.file;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyh on 16-9-27.
 */
public class FileLib {
    @Id
    private String oid = null;
    private String name = null;
    private String uid = null;
    private List<FileManager> fileManagerList = new ArrayList<FileManager>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FileManager> getFileManagerList() {
        return fileManagerList;
    }

    public void setFileManagerList(List<FileManager> fileManagerList) {
        this.fileManagerList = fileManagerList;
    }
}
