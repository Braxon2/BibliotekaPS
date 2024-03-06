/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import domain.KorisnickiProfil;
import java.util.List;
import repository.Repository;
import so.AbstractSO;

/**
 *
 * @author Dusan
 */
public class LoginSO extends AbstractSO{
    
    KorisnickiProfil kp;

    

    @Override
    public void validate(Object obj) throws Exception {
        if(obj == null || !(obj instanceof KorisnickiProfil)){
            throw new Exception("Nije poslat korisnicki profil");
        }
        KorisnickiProfil korPro = (KorisnickiProfil) obj;
        if(korPro.getUsername() == null || korPro.getUsername().isEmpty()){
            throw new Exception("Username ne sme biti prazan!");
        }
        if(korPro.getPassword()== null || korPro.getPassword().isEmpty()){
            throw new Exception("Password ne sme biti prazan!");
        }
    }

    @Override
    protected void executeOperation(Object param, String kljuc) throws Exception {
        List<KorisnickiProfil> lista  = broker.getAll((KorisnickiProfil)param);
        
        for (KorisnickiProfil k : lista) {
            if(k.equals((KorisnickiProfil) param)){
                kp = k;
                return;
            }
        }
        kp = null;
    }

    public KorisnickiProfil getKp() {
        return kp;
    }
    
    
    
}
