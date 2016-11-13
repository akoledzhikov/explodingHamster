package org.hamster.dao;


import org.hamster.model.runtime.Content;
import org.springframework.data.repository.CrudRepository;


public interface ContentRepository
    extends CrudRepository<Content, Long>
{

}
