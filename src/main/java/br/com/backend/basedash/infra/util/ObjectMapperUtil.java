package br.com.backend.basedash.infra.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ObjectMapperUtil {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Logger LOGGER = Logger.getLogger(ObjectMapperUtil.class.getName());
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // Configuração inicial do ModelMapper
        MODEL_MAPPER.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    }

    /**
     * Método de mapeamento personalizado usando ModelMapper.
     *
     * @param source Objeto de origem
     * @param target Objeto de destino
     * @param <S> Tipo do objeto de origem
     * @param <T> Tipo do objeto de destino
     * @return Objeto de destino com valores mapeados
     */
    public <S, T> T map(final S source, T target) {
        if (source == null || target == null) {
            return target;
        }
        try {
            MODEL_MAPPER.map(source, target);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Erro de mapeamento: ", ex);
        }
        return target;
    }

    /**
     * Método de mapeamento geral usando ModelMapper.
     *
     * @param source Objeto de origem
     * @param targetClass Classe do objeto de destino
     * @param <S> Tipo do objeto de origem
     * @param <T> Tipo do objeto de destino
     * @return Objeto de destino mapeado
     */
    public <S, T> T map(final S source, final Class<T> targetClass) {
        return MODEL_MAPPER.map(source, targetClass);
    }

    /**
     * Método para mapear uma coleção de objetos para uma lista de objetos do tipo destino.
     *
     * @param sourceList Lista de objetos de origem
     * @param targetClass Classe do objeto de destino
     * @param <D> Tipo do objeto de destino
     * @param <T> Tipo do objeto de origem
     * @return Lista de objetos de destino
     */
    public <D, T> List<D> mapAll(final Collection<T> sourceList, Class<D> targetClass) {
        return sourceList.stream()
                .map(source -> map(source, targetClass))
                .collect(Collectors.toList());
    }

    /**
     * Converte um objeto para uma string JSON usando o ObjectMapper do Jackson.
     *
     * @param obj Objeto a ser convertido
     * @return String JSON representando o objeto
     */
    public String objectToJson(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro na conversão para JSON: ", e);
            return "";
        }
    }

    /**
     * Converte um corpo de resposta JSON para um objeto Java.
     *
     * @param json Corpo da resposta JSON
     * @param targetClass Classe do objeto de destino
     * @param <T> Tipo do objeto de destino
     * @return Objeto Java correspondente ao JSON
     */
    public <T> T jsonToObject(String json, Class<T> targetClass) {
        try {
            return OBJECT_MAPPER.readValue(json, targetClass);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro na conversão de JSON para objeto: ", e);
            return null;
        }
    }
}
