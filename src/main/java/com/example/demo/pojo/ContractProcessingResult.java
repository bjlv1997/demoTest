package com.example.demo.pojo;


public class ContractProcessingResult  {


    private Long contractId;


    private String pcnum;


    private String contractcode;


    private String contractname;


    private String contractstatus;


    private String remarks;


    private String signurl;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getPcnum() {
        return pcnum;
    }

    public void setPcnum(String pcnum) {
        this.pcnum = pcnum;
    }

    public String getContractcode() {
        return contractcode;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return contractname;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContractstatus() {
        return contractstatus;
    }

    public void setContractstatus(String contractstatus) {
        this.contractstatus = contractstatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSignurl() {
        return signurl;
    }

    public void setSignurl(String signurl) {
        this.signurl = signurl;
    }
}
