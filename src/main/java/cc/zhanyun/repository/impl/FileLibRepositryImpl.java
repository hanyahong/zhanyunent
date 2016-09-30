package cc.zhanyun.repository.impl;

import cc.zhanyun.model.file.FileLib;
import cc.zhanyun.model.file.FileManager;
import cc.zhanyun.repository.FileLibRepositry;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyh on 16-9-27.
 */
public class FileLibRepositryImpl {

    @Autowired
    private FileLibRepositry fileLibRepositry;
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增文件库/修改
     *
     * @param fileLib
     */
    public void addFileLib(FileLib fileLib) {
        fileLibRepositry.save(fileLib);
    }

    /**
     * 删除文件库
     *
     * @param oid
     */
    public void delFileLib(String oid) {
        fileLibRepositry.delete(oid);
    }

    /**
     * 查询单条库
     *
     * @param oid
     */
    public FileLib selFileLib(String oid) {


        return fileLibRepositry.findOne(oid);
    }

    /**
     * 查询单条库 按照fileoid
     *
     * @param oid
     */
    public FileLib selFileLibByUidAndFileOid(String uid, String oid) {


        return fileLibRepositry.findOne(oid);
    }

    /**
     * 查询单条库中文件
     *
     * @param oid
     */
    public List<FileManager> selFileLibFileList(String oid) {
        List<FileManager> fileManagers = null;
        FileLib fileLib = fileLibRepositry.findOne(oid);
        if (fileLib != null) {
            fileManagers = fileLib.getFileManagerList();
        } else {
            fileManagers = new ArrayList<FileManager>();
        }
        return fileManagers;
    }

    /**
     * 单条删除文件库单条文件信息
     *
     * @param oid
     * @param fileoid
     */
    public Integer delOneOfFileInFileLib(String oid, String fileoid) {
        Integer integer = 0;
        try {
            Query query = Query.query(Criteria.where("_id").is(oid)
                    .and("fileManagerList._id").is(fileoid));
            Update update = new Update();
            update.pull("fileManagerList", new BasicDBObject("_id", fileoid));
            this.mongoTemplate.updateFirst(query, update, FileLib.class);
            integer = 1;
        } catch (Exception e) {
            e.printStackTrace();
            integer = 0;
        }
        return integer;
    }

    /**
     * 单条增加文件信息
     *
     * @param fileManager
     * @param oid
     * @return
     */
    public Integer addOneOfFileInFileLib(FileManager fileManager, String oid) {
        try {
            Query query = Query.query(Criteria.where("_id").is(oid));
            Update update = new Update();
            update.addToSet("fileManagerList", fileManager);
            this.mongoTemplate.upsert(query, update, FileLib.class);
        } catch (Exception e) {
            return Integer.valueOf(0);
        }
        return Integer.valueOf(1);
    }


}