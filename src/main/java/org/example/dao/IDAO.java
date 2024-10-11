package org.example.dao;


import java.util.List;

public interface IDAO<T> {
    public Integer cadastrar(T entity) throws Exception;

    public T consultar(String codigo) throws Exception;

    public Integer excluir(String codigo) throws Exception;

    public Integer atualizar(T entity) throws Exception;

    public List<T> retornaTodos() throws Exception;
}
