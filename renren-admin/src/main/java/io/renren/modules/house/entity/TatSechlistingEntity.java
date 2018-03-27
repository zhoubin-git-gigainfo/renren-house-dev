/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.house.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;


/**
 * 挂牌房源
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@TableName("tat_sechlisting")
public class TatSechlistingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    private String sseq;
    private String appseq;
    private String subject;
    private String distid;
    private String addr;
    private String mdlist;
    private String icno;
    private String barea;
    private String hlayout;
    private String orientations;
    private String renovation;
    private String price;
    private String memo;
    private Date sDate;
    private Date eDate;
    private String bfete;
    private String hdesc;
    private String paytype;
    private String monthcount;
    private String houseuse;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDistid() {
        return distid;
    }

    public void setDistid(String distid) {
        this.distid = distid;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMdlist() {
        return mdlist;
    }

    public void setMdlist(String mdlist) {
        this.mdlist = mdlist;
    }

    public String getIcno() {
        return icno;
    }

    public void setIcno(String icno) {
        this.icno = icno;
    }

    public String getBarea() {
        return barea;
    }

    public void setBarea(String barea) {
        this.barea = barea;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public String getBfete() {
        return bfete;
    }

    public void setBfete(String bfete) {
        this.bfete = bfete;
    }

    public String getHdesc() {
        return hdesc;
    }

    public void setHdesc(String hdesc) {
        this.hdesc = hdesc;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getMonthcount() {
        return monthcount;
    }

    public void setMonthcount(String monthcount) {
        this.monthcount = monthcount;
    }

    public String getHouseuse() {
        return houseuse;
    }

    public void setHouseuse(String houseuse) {
        this.houseuse = houseuse;
    }

    public String getXzId() {
        return xzId;
    }

    public void setXzId(String xzId) {
        this.xzId = xzId;
    }
}
