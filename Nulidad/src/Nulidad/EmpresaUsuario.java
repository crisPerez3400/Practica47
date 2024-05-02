package Nulidad;

import java.util.ArrayList;

class EmpresaUsuario {
	
	
    private String codEmpresa;
    private String nomEmpresa;
    private String nomUsuario;

    public EmpresaUsuario(String codEmpresa, String nomEmpresa, String nomUsuario) {
        this.codEmpresa = codEmpresa;
        this.nomEmpresa = nomEmpresa;
        this.nomUsuario = nomUsuario;
    }

    public String getNombreEmpresaUsuario() {
        return this.nomEmpresa;
    }

    //public String getNombreClienteUsuario() {
    //    return this.nomEmpresa;
   // }

    public String getNombreUsuario() {
        return this.nomUsuario;
    }
}
