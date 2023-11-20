package lshh.apirepository.common.resourcer;

import java.util.List;
import lshh.apirepository.dto.api.ResourcerContextDto;

public interface ResourcerManager {
    // JdbcResourcerManager
    // RestApiResourcerManager
    <T extends ResourcerContext> T getResourcer(int id) throws Exception;

    boolean deallocateResourcer(int id);
    // 컨텍스트를 팩토리에서 만들어져, 컨테이너 안에 담는다.
    // 컨테이너는 매니저가 관리

    List<ResourcerContextDto> getContextList();
}
