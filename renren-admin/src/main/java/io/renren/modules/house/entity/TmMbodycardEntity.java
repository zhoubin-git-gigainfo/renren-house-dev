package io.renren.modules.house.entity;


import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 中介公司
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date -- ::
 */
@TableName("tm_mbodycard")

public class TmMbodycardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mdId;
    private String vDate;
    private String fDate;
    private String icType;
    private String icNo;
    private String icOrg;
    private String mdName;
    private String mdTel;
    private String mdFax;
    private String mdAtype;
    private String mdraddr;
    private String mdaddr;
    private String mPost;
    private String mdMail;
    private String mdHurl;
    private String moKind;
    private String moType;
    private String moBoss;
    private String moManger;
    private String moLink;
    private String moBank;
    private String moAccu;
    private String memo;
    private String mpSex;
    private String mpMobil;
    private String mpBirth;
    private String mdType;
    private String mdInfo;
    private String newmid;
    private String holderid;
    private String mdAname;
    private String ismart;
    private String sysUserid;
    private String mseq;
    private String constraint;
    private String primary;
    private String qualitype;
    private String qualigrade;
    private String mdBossname;
    private String mdRegcapital;
    private String mdRealcapital;
    private String mdIssdate;
    private Date sDate;
    private Date eDate;
    private String currency;
    private String iscrtag;
    private String mdEducation;
    private String moWays;
    private String sseq;
    private String dsseq;
    private String jobtype;
    private String cardId;
    private String yearno;
    private String wordno;
    private String parentmid;
    private String mdSname;
    private String xzId;
    private String credate;
    private String oxzId;
    private String aptDiscause;


    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getvDate() {
        return vDate;
    }

    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public String getIcType() {
        return icType;
    }

    public void setIcType(String icType) {
        this.icType = icType;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
    }

    public String getIcOrg() {
        return icOrg;
    }

    public void setIcOrg(String icOrg) {
        this.icOrg = icOrg;
    }

    public String getMdName() {
        return mdName;
    }

    public void setMdName(String mdName) {
        this.mdName = mdName;
    }

    public String getMdTel() {
        return mdTel;
    }

    public void setMdTel(String mdTel) {
        this.mdTel = mdTel;
    }

    public String getMdFax() {
        return mdFax;
    }

    public void setMdFax(String mdFax) {
        this.mdFax = mdFax;
    }

    public String getMdAtype() {
        return mdAtype;
    }

    public void setMdAtype(String mdAtype) {
        this.mdAtype = mdAtype;
    }

    public String getMdraddr() {
        return mdraddr;
    }

    public void setMdraddr(String mdraddr) {
        this.mdraddr = mdraddr;
    }

    public String getMdaddr() {
        return mdaddr;
    }

    public void setMdaddr(String mdaddr) {
        this.mdaddr = mdaddr;
    }

    public String getmPost() {
        return mPost;
    }

    public void setmPost(String mPost) {
        this.mPost = mPost;
    }

    public String getMdMail() {
        return mdMail;
    }

    public void setMdMail(String mdMail) {
        this.mdMail = mdMail;
    }

    public String getMdHurl() {
        return mdHurl;
    }

    public void setMdHurl(String mdHurl) {
        this.mdHurl = mdHurl;
    }

    public String getMoKind() {
        return moKind;
    }

    public void setMoKind(String moKind) {
        this.moKind = moKind;
    }

    public String getMoType() {
        return moType;
    }

    public void setMoType(String moType) {
        this.moType = moType;
    }

    public String getMoBoss() {
        return moBoss;
    }

    public void setMoBoss(String moBoss) {
        this.moBoss = moBoss;
    }

    public String getMoManger() {
        return moManger;
    }

    public void setMoManger(String moManger) {
        this.moManger = moManger;
    }

    public String getMoLink() {
        return moLink;
    }

    public void setMoLink(String moLink) {
        this.moLink = moLink;
    }

    public String getMoBank() {
        return moBank;
    }

    public void setMoBank(String moBank) {
        this.moBank = moBank;
    }

    public String getMoAccu() {
        return moAccu;
    }

    public void setMoAccu(String moAccu) {
        this.moAccu = moAccu;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMpSex() {
        return mpSex;
    }

    public void setMpSex(String mpSex) {
        this.mpSex = mpSex;
    }

    public String getMpMobil() {
        return mpMobil;
    }

    public void setMpMobil(String mpMobil) {
        this.mpMobil = mpMobil;
    }

    public String getMpBirth() {
        return mpBirth;
    }

    public void setMpBirth(String mpBirth) {
        this.mpBirth = mpBirth;
    }

    public String getMdType() {
        return mdType;
    }

    public void setMdType(String mdType) {
        this.mdType = mdType;
    }

    public String getMdInfo() {
        return mdInfo;
    }

    public void setMdInfo(String mdInfo) {
        this.mdInfo = mdInfo;
    }

    public String getNewmid() {
        return newmid;
    }

    public void setNewmid(String newmid) {
        this.newmid = newmid;
    }

    public String getHolderid() {
        return holderid;
    }

    public void setHolderid(String holderid) {
        this.holderid = holderid;
    }

    public String getMdAname() {
        return mdAname;
    }

    public void setMdAname(String mdAname) {
        this.mdAname = mdAname;
    }

    public String getIsmart() {
        return ismart;
    }

    public void setIsmart(String ismart) {
        this.ismart = ismart;
    }

    public String getSysUserid() {
        return sysUserid;
    }

    public void setSysUserid(String sysUserid) {
        this.sysUserid = sysUserid;
    }

    public String getMseq() {
        return mseq;
    }

    public void setMseq(String mseq) {
        this.mseq = mseq;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getQualitype() {
        return qualitype;
    }

    public void setQualitype(String qualitype) {
        this.qualitype = qualitype;
    }

    public String getQualigrade() {
        return qualigrade;
    }

    public void setQualigrade(String qualigrade) {
        this.qualigrade = qualigrade;
    }

    public String getMdBossname() {
        return mdBossname;
    }

    public void setMdBossname(String mdBossname) {
        this.mdBossname = mdBossname;
    }

    public String getMdRegcapital() {
        return mdRegcapital;
    }

    public void setMdRegcapital(String mdRegcapital) {
        this.mdRegcapital = mdRegcapital;
    }

    public String getMdRealcapital() {
        return mdRealcapital;
    }

    public void setMdRealcapital(String mdRealcapital) {
        this.mdRealcapital = mdRealcapital;
    }

    public String getMdIssdate() {
        return mdIssdate;
    }

    public void setMdIssdate(String mdIssdate) {
        this.mdIssdate = mdIssdate;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIscrtag() {
        return iscrtag;
    }

    public void setIscrtag(String iscrtag) {
        this.iscrtag = iscrtag;
    }

    public String getMdEducation() {
        return mdEducation;
    }

    public void setMdEducation(String mdEducation) {
        this.mdEducation = mdEducation;
    }

    public String getMoWays() {
        return moWays;
    }

    public void setMoWays(String moWays) {
        this.moWays = moWays;
    }

    public String getSseq() {
        return sseq;
    }

    public void setSseq(String sseq) {
        this.sseq = sseq;
    }

    public String getDsseq() {
        return dsseq;
    }

    public void setDsseq(String dsseq) {
        this.dsseq = dsseq;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getYearno() {
        return yearno;
    }

    public void setYearno(String yearno) {
        this.yearno = yearno;
    }

    public String getWordno() {
        return wordno;
    }

    public void setWordno(String wordno) {
        this.wordno = wordno;
    }

    public String getParentmid() {
        return parentmid;
    }

    public void setParentmid(String parentmid) {
        this.parentmid = parentmid;
    }

    public String getMdSname() {
        return mdSname;
    }

    public void setMdSname(String mdSname) {
        this.mdSname = mdSname;
    }

    public String getXzId() {
        return xzId;
    }

    public void setXzId(String xzId) {
        this.xzId = xzId;
    }

    public String getCredate() {
        return credate;
    }

    public void setCredate(String credate) {
        this.credate = credate;
    }

    public String getOxzId() {
        return oxzId;
    }

    public void setOxzId(String oxzId) {
        this.oxzId = oxzId;
    }

    public String getAptDiscause() {
        return aptDiscause;
    }

    public void setAptDiscause(String aptDiscause) {
        this.aptDiscause = aptDiscause;
    }
}
