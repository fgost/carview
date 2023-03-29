package fghost.carview.v1.accesGroup.mapper;

import fghost.carview.v1.accesGroup.domain.AccessGroupEntity;
import fghost.carview.v1.accesGroup.model.request.AccessGroupRequestInput;
import fghost.carview.v1.accesGroup.model.request.AccessGroupRequestInputPermissions;
import fghost.carview.v1.users.model.request.UserRequestProfileUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface AccessGroupRequestMapper {
    AccessGroupRequestMapper INSTANCE = Mappers.getMapper(AccessGroupRequestMapper.class);

    AccessGroupEntity mapModelToEntity(AccessGroupRequestInput request);

    Collection<AccessGroupEntity> mapModelToEntityCollection(Collection<AccessGroupRequestInput> profilesInput);

    AccessGroupEntity mapModelToEntity(AccessGroupRequestInputPermissions request);

    AccessGroupEntity mapModelToEntity(UserRequestProfileUpdate requestProfileUpdate);

    Collection<AccessGroupEntity> mapUserRequestProfileToEntityCollection(Collection<UserRequestProfileUpdate> requestProfileUpdate);
}
