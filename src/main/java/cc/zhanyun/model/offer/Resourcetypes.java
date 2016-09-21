package cc.zhanyun.model.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;

@ApiModel(description = "")
public class Resourcetypes {
    @Id
    private String name = null;

    private List<Selectedresources> selectedresources = new ArrayList();
    private Boolean enabled = null;
    private String typetotal = null;

    public String getTypetotal() {
        return this.typetotal;
    }

    public void setTypetotal(String typetotal) {
        this.typetotal = typetotal;
    }

    @ApiModelProperty("设备类别名称")
    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty("")
    @JsonProperty("selectedresources")
    public List<Selectedresources> getSelectedresources() {
        return this.selectedresources;
    }

    public void setSelectedresources(List<Selectedresources> selectedresources) {
        this.selectedresources = selectedresources;
    }

    @ApiModelProperty("此资设备类别是否启用")
    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }
        Resourcetypes resourcetypes = (Resourcetypes) o;
        if ((Objects.equals(this.name, resourcetypes.name))
                && (Objects.equals(this.selectedresources,
                resourcetypes.selectedresources))) {
        }

        return Objects.equals(this.enabled, resourcetypes.enabled);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.selectedresources,
                this.enabled});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Resourcetypes {\n");

        sb.append("  name: ").append(this.name).append("\n");
        sb.append("  selectedresources: ").append(this.selectedresources)
                .append("\n");
        sb.append("  enabled: ").append(this.enabled).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
