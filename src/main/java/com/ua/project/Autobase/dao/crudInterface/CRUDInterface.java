package com.ua.project.Autobase.dao.crudInterface;

import java.util.List;

public interface CRUDInterface <E> {
    /**
     *
     * @param item
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int save(E item);

    /**
     *
     * @param items
     * @return after exception returns empty int array, otherwise returns array of codes from Statement
     */
    int[] saveMany(List<E> items);

    /**
     *
     * @param item
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int update(E item);

    /**
     *
     * @return if there is no data in DB returns empty List otherwise returns array from searching result
     */
    List<E> findAll();

    /**
     *
     * @param item
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int delete(E item);

    /**
     *
     * @return after exception returns -1, otherwise returns code from Statement
     */
    int deleteAll();
}
