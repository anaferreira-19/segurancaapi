package com.etec.tcc.apiseguranca.repository.alerta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.etec.tcc.apiseguranca.model.Alerta;
import com.etec.tcc.apiseguranca.repository.filter.AlertaFilter;

public class AlertaRepositoryImpl implements AlertaRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	public Page<Alerta> filtrar(AlertaFilter alertaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Alerta> criteria = builder.createQuery(Alerta.class);
		
		Root<Alerta> root = criteria.from(Alerta.class);
		
		Predicate[] predicates = criarRestricoes(alertaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.orderBy(builder.desc(root.get("data")));
		
		TypedQuery<Alerta> query = manager.createQuery(criteria);
		
		adicionarRestricaoDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(alertaFilter));
	}
	
	private void adicionarRestricaoDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(AlertaFilter alertaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Alerta> root = criteria.from(Alerta.class);
		
		Predicate[] predicates = criarRestricoes(alertaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
		
		
	}
	
	private Predicate[] criarRestricoes(AlertaFilter alertaFilter, CriteriaBuilder builder, Root<Alerta> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(alertaFilter.getAlertade() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("alerta"), alertaFilter.getAlertade()));
		}
		if(alertaFilter.getAlertaate() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("alerta"), alertaFilter.getAlertaate()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
