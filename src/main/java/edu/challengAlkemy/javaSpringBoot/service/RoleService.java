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
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleDtoToRole mapper;

    public Role createRole(RoleDto dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new CustomException("Invalid, this role already exists", HttpStatus.BAD_REQUEST);
        }
        return roleRepository.save(mapper.apply(dto));
    }

    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Role> addRoleToUser(List<Long> listRolesId) {
        List<Role> listRoles = listRolesId.stream().map((id) -> getRoleById(id)).collect(Collectors.toList());
        return listRoles;
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
