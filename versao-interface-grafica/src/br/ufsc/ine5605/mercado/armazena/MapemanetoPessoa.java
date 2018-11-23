package br.ufsc.ine5605.mercado.armazena;

import br.ufsc.ine5605.mercado.model.Pessoa;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MapemanetoPessoa {
    private HashMap<String, Pessoa> cachePessoas = new HashMap<>();
    private final String filename = "usuariosMercado.user";

    public MapemanetoPessoa(){
        load();
    }
    
    public Pessoa get(String idPessoa){
        return cachePessoas.get(idPessoa);
    }

    public void put(Pessoa pessoa){
        cachePessoas.put(pessoa.getCpf(), pessoa);
        persist();
    }

    public ArrayList<Pessoa> getList(){
        return  new ArrayList(cachePessoas.values());
    }

    public void persist(){
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cachePessoas);
            oo.flush();
            fout.flush();

            oo.close();
            fout.close();

        } catch (FileNotFoundException ex){
            System.out.println("arquivo nao encontrado");
        } catch (IOException exx){
            System.out.println(exx);
        }
    }
    
    public  void load(){
        try {
            FileInputStream fin = new FileInputStream(filename);
            ObjectInputStream oi = new ObjectInputStream(fin);
            this.cachePessoas = (HashMap<String, Pessoa>) oi.readObject();

            oi.close();
            fin.close();
        } catch (ClassNotFoundException x){
            System.out.println("Arquivo invalido");
            persist();
        } catch (FileNotFoundException ex){
            System.out.println("Arquivo nao encontrado");
        } catch (IOException e){
            System.out.println(e);
        }
    }
    
    public void remove(Pessoa pessoa){
        cachePessoas.remove(pessoa.getCpf());
    }
}