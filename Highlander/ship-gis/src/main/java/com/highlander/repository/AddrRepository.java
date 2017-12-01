package com.highlander.repository;

import com.highlander.entity.Addr;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddrRepository extends BaseRepository<Addr> {
    List<Addr> findByParentId(Long parentId);
}
