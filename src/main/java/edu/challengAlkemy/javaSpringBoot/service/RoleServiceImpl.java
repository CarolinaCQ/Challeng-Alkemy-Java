package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.RoleDto;
import edu.challengAlkemy.javaSpringBoot.entity.Role;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import edu.challengAlkemy.javaSpringBoot.mapper.RoleDtoToRole;
import edu.challengAlkemy.javaSpringBoot.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleDtoToRole mapper;

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new CustomException("Role not found.", HttpStatus.NOT_FOUND);
        }
        return roleRepository.findById(id).get();
    }

    @Override
    public Role createRole(RoleDto dto) {
        if (!roleRepository.existsByName(dto.getName())) {
            throw new CustomException("Invalid, this role already exists", HttpStatus.BAD_REQUEST);
        }
        return roleRepository.save(mapper.apply(dto));
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new CustomException("Role not found.", HttpStatus.NOT_FOUND);
        }
        roleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Role> addRoleToUser(List<Long> listRolesId) {
        List<Role> listRoles = listRolesId.stream().map((id) -> getRoleById(id)).collect(Collectors.toList());
        return listRoles;
    }

}
