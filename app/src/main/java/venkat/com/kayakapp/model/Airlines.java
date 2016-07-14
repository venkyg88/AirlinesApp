package venkat.com.kayakapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by venkatgonuguntala on 3/19/16.
 */
public class Airlines {

    @JsonProperty("__clazz")
    private String Clazz;
    @JsonProperty("code")
    private String code;
    @JsonProperty("logoURL")
    private String logoURL;
    @JsonProperty("name")
    private String name;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("site")
    private String site;

    private boolean isChecked = false;

    /**
     *
     * @return
     * The Clazz
     */
    @JsonProperty("__clazz")
    public String getClazz() {
        return Clazz;
    }

    /**
     *
     * @param Clazz
     * The __clazz
     */
    @JsonProperty("__clazz")
    public void setClazz(String Clazz) {
        this.Clazz = Clazz;
    }

    /**
     *
     * @return
     * The code
     */
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The logoURL
     */
    @JsonProperty("logoURL")
    public String getLogoURL() {
        return logoURL;
    }

    /**
     *
     * @param logoURL
     * The logoURL
     */
    @JsonProperty("logoURL")
    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The phone
     */
    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The site
     */
    @JsonProperty("site")
    public String getSite() {
        return site;
    }

    /**
     *
     * @param site
     * The site
     */
    @JsonProperty("site")
    public void setSite(String site) {
        this.site = site;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isChecked(){
        return isChecked;
    }
}