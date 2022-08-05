package org.BDIII_HB_TB;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
	@SuppressWarnings("deprecation")
	public static void main( String[] args )
    {	
    	//-------------Responsável por iniciar a sessão com o PGAdmin-----------------------
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//##################################################################################
		
		Endereco end1 = new Endereco("R. Comendador Alves", "3003", "São Vicente", "Macaipu");
		Endereco end2 = new Endereco("R. Cap. Rocha", "403", "Alto Alegre", "São Capulo Capital");
		
		Telefone tel1 = new Telefone("42", "34463030", "fixo");
		Telefone tel2 = new Telefone("11", "998724058", "celular");
		Telefone tel3 = new Telefone("55", "940007722", "VoIP");
		
		Cliente cl1 = new Cliente("Felipe Zorzo", "04/12/2002", 'm', "100.200.300-50", end1);
		Cliente cl2 = new Cliente("Michael Fumaca", "09/09/1989", 'm', "032.456.901-40", end2);
		
		List<Telefone> telefones1 = new ArrayList<Telefone>();
		tel1.setCliente(cl2);
		tel2.setCliente(cl2);
		telefones1.add(tel1);
		telefones1.add(tel2);
		cl2.setTelefones(telefones1);
		
		List<Telefone> telefones2 = new ArrayList<Telefone>();
		tel3.setCliente(cl1);
		telefones2.add(tel3);
		cl1.setTelefones(telefones2);
		
		Produto prod1 = new Produto("zx10f40", "Serra copo 30mm", 27.78);
		Produto prod2 = new Produto("s35c7c6", "Lima reta", 5.75);
		Produto prod3 = new Produto("f4ppx12", "Marreta 2kg", 21.80);
		
		Venda vd1 = new Venda ("aaa00001", cl1);
		
		List<Produto> produtos1 = new ArrayList<Produto>();
		prod1.setVenda(vd1);
		prod2.setVenda(vd1);
		prod3.setVenda(vd1);
		produtos1.add(prod1);
		produtos1.add(prod2);
		produtos1.add(prod3);
		vd1.setProdutos(produtos1);
		vd1.setValorTotal();
		
		session.save(cl1);
		session.save(cl2);
		session.save(end1);
		session.save(end2);
		session.save(tel1);
		session.save(tel2);
		session.save(tel3);
		session.save(vd1);
		session.save(prod1);
		session.save(prod2);
		session.save(prod3);
		
		tx.commit();
		//---------------------Encerra a sessão com Hibernate-------------------------------
		session.close();
		sessionFactory.close();
		//##################################################################################
		
    }
}