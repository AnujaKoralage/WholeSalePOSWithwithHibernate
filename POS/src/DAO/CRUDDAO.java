package DAO;

import Entities.SuperEntity;

import java.util.List;

public interface CRUDDAO<T extends SuperEntity,ID> extends SuperDAO {

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID entityID) throws Exception;

    List<T> findAll() throws Exception;

    T find(ID entityID) throws Exception;

}
