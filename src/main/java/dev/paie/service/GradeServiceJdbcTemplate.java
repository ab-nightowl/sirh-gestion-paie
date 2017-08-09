package dev.paie.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dev.paie.entite.Grade;

@Service
public class GradeServiceJdbcTemplate implements GradeService {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public GradeServiceJdbcTemplate(DataSource dataSource) {
		super();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void sauvegarder(Grade nouveauGrade) {
		String sql = "INSERT INTO grades (code,nb_heures_base,taux_base) VALUES (?,?,?)";
		this.jdbcTemplate.update(sql, nouveauGrade.getCode(), nouveauGrade.getNbHeuresBase(), nouveauGrade.getTauxBase());
	}

	@Override
	public void mettreAJour(Grade grade) {
		String sql = "UPDATE grades SET nb_heures_base=?, taux_base=? WHERE code=?";
		this.jdbcTemplate.update(sql, grade.getNbHeuresBase(), grade.getTauxBase(), grade.getCode());
	}

	@Override
	public List<Grade> lister() {
		String sql = "SELECT * FROM grades";
		RowMapper<Grade> rowMapper = (resultSet, rowNum) -> {
			Grade g = new Grade();
			g.setId(resultSet.getInt("id"));
			g.setCode(resultSet.getString("code"));
			g.setNbHeuresBase(resultSet.getBigDecimal("nb_heures_base"));
			g.setTauxBase(resultSet.getBigDecimal("taux_base"));
			return g;
		};
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void effacerDonnees() {
		this.jdbcTemplate.update("TRUNCATE grades");
	}

}
