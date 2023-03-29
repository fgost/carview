package fghost.carview.v1.accesGroup.mapper;

import fghost.carview.v1.accesGroup.domain.AccessGroupEntity;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponse;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseSummary;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseWithPermissions;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseWithProfiles;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface AccessGroupResponseMapper {

    AccessGroupResponseMapper INSTANCE = Mappers.getMapper(AccessGroupResponseMapper.class);

    AccessGroupResponseSummary mapEntityToResponseSummary(AccessGroupEntity entity);

    Collection<AccessGroupResponseSummary> mapEntityToResponseSummaryCollection(Collection<AccessGroupEntity> entities);

    AccessGroupResponseWithPermissions mapEntityToResponseWithPermissions(AccessGroupEntity entity);


    AccessGroupResponseWithProfiles mapEntityToModel (AccessGroupEntity entity);

    AccessGroupResponse mapEntityToResponse (AccessGroupEntity entity);



    Collection<AccessGroupResponseWithProfiles> mapEntityToModelCollection (Collection<AccessGroupEntity> entities);

    Collection<AccessGroupResponseWithPermissions> mapEntityToResponseWithPermissionsCollection(Collection<AccessGroupEntity> entities);
}
