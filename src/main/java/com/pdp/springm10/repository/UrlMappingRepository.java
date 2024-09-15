package com.pdp.springm10.repository;

import com.pdp.springm10.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aliabbos Ashurov
 * @since 15/September/2024  16:11
 **/
@Repository
public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    UrlMapping findByShortUrl(String shortUrl);

    UrlMapping findByLongUrl(String longUrl);
}

