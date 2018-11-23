package br.ufsc.ine5605.mercado.armazena;

import br.ufsc.ine5605.mercado.model.Loja;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MapeamentoLoja {
    private HashMap<Integer, Loja> cacheLoja = new HashMap<>();
    private final String filename = "mercado.loja";

    public MapeamentoLoja(){
        load();
    }
        
    public Loja get(Integer idLoja){
        return cacheLoja.get(idLoja);
    }

    public void put(Loja loja){
        cacheLoja.put(loja.getCodigo(), loja);
        persist();
    }

    public ArrayList<Loja> getList(){
        return  new ArrayList<>(cacheLoja.values());
    }

    public void persist(){
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oo = new ObjectOutputStream(fout);
            oo.writeObject(cacheLoja);
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
            this.cacheLoja = (HashMap<Integer, Loja>) oi.readObject();
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
        
    public void remove(Loja loja){
        cacheLoja.remove(loja.getCodigo());
    }    
}