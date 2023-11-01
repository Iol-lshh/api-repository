package lshh.apirepository.common.pipeline;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.experimental.Accessors;
import lshh.apirepository.dto.api.QueryDto;
import lshh.apirepository.dto.request.QueryArgumentDto;
import lshh.apirepository.dto.request.QueryMsgDto;
import lshh.apirepository.service.ServiceTemplate.Status;
import lshh.apirepository.common.resourcer.ResourcerContext;

import java.util.List;
import java.util.Map;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Accessors(chain = true, fluent = true)
@Data
public class DbPipelineStep implements PipelineStep{

    ResourcerContext resourcer;
    
    ResourcerType resourcerType = ResourcerType.DB;
    ProcessType processType;
    
    QueryDto queryDto;
    List<QueryArgumentDto<Object>> argumentDtos;

    Status status = Status.WAIT;
    List<Map <String, Object>> result;
    
    @Override
    public QueryDto query(){
        return this.queryDto;
    }

    @Override
    public DbPipelineStep query(QueryDto queryDto){
        this.queryDto = queryDto;
        return this;
    }

    @Override
    public List<QueryArgumentDto<Object>> arguments(){
        return this.argumentDtos;
    }

    @Override
    public DbPipelineStep arguments(List<QueryArgumentDto<Object>> dtos){
        this.argumentDtos = dtos;
        return this;
    }

    @Override
    public Status act() throws PipelineProcessFailException{
        QueryMsgDto requestDto = new QueryMsgDto()
            .query(this.queryDto.contents())
            .arguments(this.argumentDtos);

        try{
            this.result = resourcer.getResource(requestDto);
            
        }catch(Exception e){
            this.status = Status.FAIL;
            throw new PipelineProcessFailException(e.getMessage());
        }
        return Status.OK;
    }
}
