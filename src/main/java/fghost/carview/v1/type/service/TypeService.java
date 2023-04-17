package fghost.carview.v1.type.service;

import fghost.carview.domain.Constants;
import fghost.carview.exception.domain.ObjectNotFoundException;
import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.v1.type.domain.TypeEntity;
import fghost.carview.v1.type.domain.TypeEnum;
import fghost.carview.v1.type.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class TypeService {
    private final TypeRepository repository;

    public Page<TypeEntity> findAll(Pageable pageable, TypeEnum typeName) {
        boolean hasName = typeName != null;
        if(hasName)
            return repository.findByTypeNameContainingIgnoreCase(pageable, typeName);

        return repository.findAll(pageable);
    }

    public TypeEntity findByCode(String code) {
        var entity = repository.findByCode(code)
                .orElseThrow(() -> new ObjectNotFoundException(Constants.TYPE_NOT_FOUND));
        return entity;
    }

    @Transactional
    public TypeEntity insert(TypeEntity typeEntity) {
        try {
            return repository.save(typeEntity);
        }catch (DataIntegrityViolationException ex) {
            throw ExceptionUtils.buildSameIdentifierException(Constants.TYPE_DUPLICATED);
        }
    }

    @Transactional
    public TypeEntity update(String code, TypeEntity entity) {
        var existentEntity = findByCode(code);
        existentEntity.setCode(code);
        existentEntity.setTypeName(entity.getTypeName());

        var dto = existentEntity;
        try {
            return repository.save(dto);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.TYPE_NOT_PERSISTED);
        }
    }

    @Transactional
    public void deleteByCode(String code) {
        try{
            var entity = findByCode(code);
            repository.delete(entity);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.TYPE_DELETION_ERROR);
        }
    }
}
