package com.route.application.service;

import org.mapstruct.factory.Mappers;

class DomainMapperTest {
    private final DomainMapper domainMapper = Mappers.getMapper(DomainMapper.class);

//    @Test
//    void should_map_to_tsp_domain(){
//        // given
//        List<CityDomain> cityDomainList = List.of(CityDomain.builder().build());
//        // when
//        TspRouteDomain tspRouteDomain = domainMapper.toTspRouteDomain(cityDomainList);
//        // then
//        assertThat(tspRouteDomain.getRoute()).isEqualTo(cityDomainList);
//    }
}