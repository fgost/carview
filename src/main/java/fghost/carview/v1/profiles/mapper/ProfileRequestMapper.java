package fghost.carview.v1.profiles.mapper;

import fghost.carview.v1.accesGroup.domain.AccessGroupEntity;
import fghost.carview.v1.profiles.domain.ProfileEntity;
import fghost.carview.v1.profiles.model.request.ProfileRequestInput;
import fghost.carview.v1.profiles.model.request.ProfileRequestUpdateAccessGroup;
import fghost.carview.v1.users.model.request.UserRequestProfileUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface ProfileRequestMapper {
    ProfileRequestMapper INSTANCE = Mappers.getMapper(ProfileRequestMapper.class);

    ProfileEntity mapModelToEntity(ProfileRequestInput request);

    Collection<ProfileEntity> mapModelToEntityCollection(Collection<ProfileRequestInput> profilesInput);

    AccessGroupEntity mapModelToEntityAccessGroups(ProfileRequestUpdateAccessGroup request);

    Collection<AccessGroupEntity> mapModelToEntityCollectionAccessGroups(Collection<ProfileRequestUpdateAccessGroup> request);

    ProfileEntity mapModelToEntity(UserRequestProfileUpdate requestProfileUpdate);

    Collection<ProfileEntity> mapUserRequestProfileToEntityCollection(Collection<UserRequestProfileUpdate> requestProfileUpdate);
}
