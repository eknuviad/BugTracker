package ca.mcgill.ecse321.bugtracker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.bugtracker.dao.ProjectRepository;
import ca.mcgill.ecse321.bugtracker.dao.UserRoleRepository;
import ca.mcgill.ecse321.bugtracker.model.Developer;
import ca.mcgill.ecse321.bugtracker.model.Project;
import ca.mcgill.ecse321.bugtracker.model.UserRole;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository pRepository;
    @Autowired
    private UserRoleRepository uRepository;
    

    @Transactional
    public Project createProjectByUserRole(String pName, UserRole ur){
        String error = "";
        if (ur == null) {
            error = error + "The user to create a Manager cannot be empty.";
        }else if (ur instanceof Developer){
            error = error + "Developers are not allowed to create projects at the moment.";
        }
        if (pName == null || pName.trim().length() == 0) {
            error = error + "The Project name cannot be empty or have spaces.";
        }
        Project p = new Project();
        p.setName(pName);
        p.setUserRole(ur);
        pRepository.save(p);

        return p;
    }


     /**
     *
     * @param iterable
     * @param <T>
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}