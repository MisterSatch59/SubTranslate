package com.dao;

import java.util.List;

/**
 * 
 * Superclasse Dao permettant l'accés à la base de données
 * @author oltenos
 *
 * @param <T> type de beans correspondant à la table de la base de données souhaité
 */
public abstract class Dao<T> {

	/**
	 * Permet d'ajouté un élément à la base de données
	 * @param beans élément à ajouter
	 * @throws DaoException
	 */
	public abstract void add(T beans) throws DaoException;

	/**
	 * Permet de supprimer un élément de la base de données
	 * @param id identifiant de l'élément
	 * @throws DaoException
	 */
	public abstract void delete(int id) throws DaoException;

	/**
	 * Permet d'obtenir la liste des beans présent dans la base de données
	 * @return liste des beans
	 * @throws DaoException
	 */
	public abstract List<T> list() throws DaoException;

	/**
	 * Permet la mise à jour d'un élément de la base de données <br/>
	 * ATTENTION : la ligen modifiée dans la base de données sera celle correspondant à l'id du bean en paramétre
	 * @param bean élément à jour
	 * @throws DaoException
	 */
	public abstract void update(T bean)  throws DaoException;

};
