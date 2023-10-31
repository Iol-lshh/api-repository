package lshh.apirepository.common.pipeline;

public class PipelineProcessFailException extends Exception {
    public PipelineProcessFailException(){
        super();
    }
    public PipelineProcessFailException(String errMsg){
        super(errMsg);
    }
}
