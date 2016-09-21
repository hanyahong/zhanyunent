package cc.zhanyun.model.vo;

import java.util.List;

public class LocationImageOidVO {
    private String oid;
    private String locationimages;
    private List<LocationImageHouseOidVO> lhlist = new java.util.ArrayList();

    public String getOid() {
        return this.oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getLocationimages() {
        return this.locationimages;
    }

    public void setLocationimages(String locationimages) {
        this.locationimages = locationimages;
    }

    public List<LocationImageHouseOidVO> getLhlist() {
        return this.lhlist;
    }

    public void setLhlist(List<LocationImageHouseOidVO> lhlist) {
        this.lhlist = lhlist;
    }
}


