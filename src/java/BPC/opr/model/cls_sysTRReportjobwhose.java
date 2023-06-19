/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BPC.opr.model;

/**
 *
 * @author witco
 */
public class cls_sysTRReportjobwhose {
    int reportjob_id;
    String worker_code;

    public cls_sysTRReportjobwhose(int reportjob_id, String worker_code) {
        this.reportjob_id = reportjob_id;
        this.worker_code = worker_code;
    }

    public int getReportjob_id() {
        return reportjob_id;
    }

    public void setReportjob_id(int reportjob_id) {
        this.reportjob_id = reportjob_id;
    }

    public String getWorker_code() {
        return worker_code;
    }

    public void setWorker_code(String worker_code) {
        this.worker_code = worker_code;
    }
    
    
}
