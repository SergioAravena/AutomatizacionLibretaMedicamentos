/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.FarmaceuticoDto;

/**
 *
 * @author Kevin
 */
public interface FarmaceuticoDao extends BaseDao<FarmaceuticoDto> {
    public String validarRut(String rut, String pass);

    public String validarPass(String pass, String rut);

    public String recuperarNombre(String rut);
}
