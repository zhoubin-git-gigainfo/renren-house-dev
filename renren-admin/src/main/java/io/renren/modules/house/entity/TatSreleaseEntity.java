package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * 挂牌房源详情
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 17-3-5 1:13:6
 */
@TableName("tat_srelease")
public class TatSreleaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.INPUT)
    private String sseq;
    private String appseq;
    private String renovadate;
    private String renovatprice;
    private String renovarprice;
    private String fdeploy;
    private String frprice;
    private String traffic;
    private String appliance;
    private String appliancerprice;
    private String around;
    private String commumication;
    private String wuyeprice;
    private String waterhead;
    private String gas;
    private String wuyememo;
    private String info;
    private String secseq;
    private String constraint;
    private String unique;
    private String lname;
    private String bfete;
    private String hlayout;
    private String orientations;
    private String renovation;
    private String price;
    private String paytype;
    private String layer;
    private String layercount;
    private String xzId;

    public String getSseq() {
        return sseq;
    }

    public void setSseq(String sseq) {
        this.sseq = sseq;
    }

    public String getAppseq() {
        return appseq;
    }

    public void setAppseq(String appseq) {
        this.appseq = appseq;
    }

    public String getRenovadate() {
        return renovadate;
    }

    public void setRenovadate(String renovadate) {
        this.renovadate = renovadate;
    }

    public String getRenovatprice() {
        return renovatprice;
    }

    public void setRenovatprice(String renovatprice) {
        this.renovatprice = renovatprice;
    }

    public String getRenovarprice() {
        return renovarprice;
    }

    public void setRenovarprice(String renovarprice) {
        this.renovarprice = renovarprice;
    }

    public String getFdeploy() {
        return fdeploy;
    }

    public void setFdeploy(String fdeploy) {
        this.fdeploy = fdeploy;
    }

    public String getFrprice() {
        return frprice;
    }

    public void setFrprice(String frprice) {
        this.frprice = frprice;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getAppliance() {
        return appliance;
    }

    public void setAppliance(String appliance) {
        this.appliance = appliance;
    }

    public String getAppliancerprice() {
        return appliancerprice;
    }

    public void setAppliancerprice(String appliancerprice) {
        this.appliancerprice = appliancerprice;
    }

    public String getAround() {
        return around;
    }

    public void setAround(String around) {
        this.around = around;
    }

    public String getCommumication() {
        return commumication;
    }

    public void setCommumication(String commumication) {
        this.commumication = commumication;
    }

    public String getWuyeprice() {
        return wuyeprice;
    }

    public void setWuyeprice(String wuyeprice) {
        this.wuyeprice = wuyeprice;
    }

    public String getWaterhead() {
        return waterhead;
    }

    public void setWaterhead(String waterhead) {
        this.waterhead = waterhead;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getWuyememo() {
        return wuyememo;
    }

    public void setWuyememo(String wuyememo) {
        this.wuyememo = wuyememo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSecseq() {
        return secseq;
    }

    public void setSecseq(String secseq) {
        this.secseq = secseq;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getBfete() {
        return bfete;
    }

    public void setBfete(String bfete) {
        this.bfete = bfete;
    }

    public String getHlayout() {
        return hlayout;
    }

    public void setHlayout(String hlayout) {
        this.hlayout = hlayout;
    }

    public String getOrientations() {
        return orientations;
    }

    public void setOrientations(String orientations) {
        this.orientations = orientations;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getLayercount() {
        return layercount;
    }

    public void setLayercount(String layercount) {
        this.layercount = layercount;
    }

    public String getXzId() {
        return xzId;
    }

    public void setXzId(String xzId) {
        this.xzId = xzId;
    }
}
