package br.com.zup.renatomelo.desafiocasadocodigo.fluxopagamento;

import br.com.zup.renatomelo.desafiocasadocodigo.paises.Estado;
import br.com.zup.renatomelo.desafiocasadocodigo.paises.Pais;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PaisTemEstadoValidator implements Validator {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaCompraRequest novaCompraRequest = (NovaCompraRequest) o;

        Pais pais = entityManager.find(Pais.class, novaCompraRequest.getPaisId());
        Estado estado = entityManager.find(Estado.class, novaCompraRequest.getEstadoId());

        if(!estado.pertenceAPais(pais)) {
            errors.rejectValue("estadoId", null, "Este estado não é do país selecionado");
        }

    }
}
