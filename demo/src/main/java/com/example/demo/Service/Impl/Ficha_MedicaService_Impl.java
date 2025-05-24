package com.example.demo.Service.Impl;

import com.example.demo.Models.FichaMedica;
import com.example.demo.Repository.ConexionPostgres;
import com.example.demo.Service.Interface.Doc_MedicosService_I;
import com.example.demo.Service.Interface.Ficha_MedicaService_I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class Ficha_MedicaService_Impl implements Ficha_MedicaService_I {


    @Override
    public void actualizarFichaMedica(FichaMedica f) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call actualizar_ficha_medica(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cn.prepareStatement(sql);

        ps.setInt(1, f.getIdUsuario());
        ps.setString(2, f.getGrupoSanguineo());
        ps.setString(3, f.getFactorRh());
        ps.setString(4, f.getAlergias());
        ps.setString(5, f.getEnfermedadesCronicas());
        ps.setString(6, f.getMedicacionPermanente());
        ps.setString(7, f.getDiscapacidades());
        ps.setBigDecimal(8, f.getPeso());
        ps.setBigDecimal(9, f.getEstatura());
        ps.setString(10, f.getAntecedentesQuirurgicos());
        ps.setString(11, f.getTipoSeguro());
        ps.setString(12, f.getNroHistoriaClinica());
        ps.executeUpdate();
        ps.close();
        cn.close();
    }
    @Override
    public List<FichaMedica> listarFichasMedicas() throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "SELECT * FROM listar_fichas_medicas()";
        PreparedStatement ps = cn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<FichaMedica> lista = new ArrayList<>();

        while (rs.next()) {
            FichaMedica f = new FichaMedica();
            f.setIdFichaMedica(rs.getInt("id_ficha_medica"));
            f.setIdUsuario(rs.getInt("id_usuario"));
            f.setGrupoSanguineo(rs.getString("grupo_sanguineo"));
            f.setFactorRh(rs.getString("factor_rh"));
            f.setAlergias(rs.getString("alergias"));
            f.setEnfermedadesCronicas(rs.getString("enfermedades_cronicas"));
            f.setMedicacionPermanente(rs.getString("medicacion_permanente"));
            f.setDiscapacidades(rs.getString("discapacidades"));
            f.setPeso(rs.getBigDecimal("peso"));
            f.setEstatura(rs.getBigDecimal("estatura"));
            f.setAntecedentesQuirurgicos(rs.getString("antecedentes_quirurgicos"));
            f.setTipoSeguro(rs.getString("tipo_seguro"));
            f.setNroHistoriaClinica(rs.getString("nro_historia_clinica"));
            lista.add(f);
        }

        rs.close();
        ps.close();
        cn.close();

        return lista;
    }
    @Override
    public FichaMedica buscarFichaMedicaPorUsuario(int idUsuario) throws Exception {
        Connection cn = ConexionPostgres.getConexion();
        String sql = "call buscar_ficha_medica_por_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        CallableStatement cs = cn.prepareCall(sql);

        cs.setInt(1, idUsuario);

        // Registrar los parámetros OUT
        cs.registerOutParameter(2, Types.VARCHAR); // grupoSanguineo
        cs.registerOutParameter(3, Types.VARCHAR); // factorRh
        cs.registerOutParameter(4, Types.VARCHAR); // alergias
        cs.registerOutParameter(5, Types.VARCHAR); // enfermedadesCronicas
        cs.registerOutParameter(6, Types.VARCHAR); // medicacionPermanente
        cs.registerOutParameter(7, Types.VARCHAR); // discapacidades
        cs.registerOutParameter(8, Types.NUMERIC); // peso
        cs.registerOutParameter(9, Types.NUMERIC); // estatura
        cs.registerOutParameter(10, Types.VARCHAR); // antecedentesQuirurgicos
        cs.registerOutParameter(11, Types.VARCHAR); // tipoSeguro
        cs.registerOutParameter(12, Types.VARCHAR); // nroHistoriaClinica

        cs.execute();

        FichaMedica ficha = new FichaMedica();
        ficha.setIdUsuario(idUsuario);
        ficha.setGrupoSanguineo(cs.getString(2));
        ficha.setFactorRh(cs.getString(3));
        ficha.setAlergias(cs.getString(4));
        ficha.setEnfermedadesCronicas(cs.getString(5));
        ficha.setMedicacionPermanente(cs.getString(6));
        ficha.setDiscapacidades(cs.getString(7));
        ficha.setPeso(cs.getBigDecimal(8));
        ficha.setEstatura(cs.getBigDecimal(9));
        ficha.setAntecedentesQuirurgicos(cs.getString(10));
        ficha.setTipoSeguro(cs.getString(11));
        ficha.setNroHistoriaClinica(cs.getString(12));

        cs.close();
        cn.close();

        return ficha;
    }

}
