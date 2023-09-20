
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import { api } from "../../../../services/api";
import { Link as RouterLink } from 'react-router-dom'
import { IPaginacao } from "../../../../shared/interfaces/IPaginacao";
import { IUsuario } from "../../../../shared/interfaces/IUsuario";
import { Link } from "@mui/icons-material";

export function TableUsuario() {

    const [usuarios, setUsuarios] = useState<IUsuario[]>([]);

    useEffect(() => {
        const token = localStorage.getItem("@suplament:token")
        console.log(token)
        api.get<IPaginacao<IUsuario>>("/usuarios", {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(resp => {
                setUsuarios(resp.data.content);
            });
    }, []);

    const excluir = (usuarioAhSerExcluido: IUsuario) => {
        api.delete(`/usuarios/${usuarioAhSerExcluido.id}`)
            .then(() => {
                const usuariosFiltrados = usuarios.filter(usuario => usuario.id !== usuarioAhSerExcluido.id);
                setUsuarios([...usuariosFiltrados])
            });
    }

    return (
        <TableContainer component={Paper} >
            <RouterLink  to="/novo">
                <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2 }}>
                    Novo
                </Button>
            </RouterLink>

            <Table sx={{marginTop: 5}}>

                <TableHead>
                    <TableRow>
                        <TableCell>Nome</TableCell>
                        <TableCell>Grupo</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Editar</TableCell>
                        <TableCell>Excluir</TableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {
                        usuarios.map(u => <TableRow key={u.id}>
                            <TableCell>{u.nome}</TableCell>
                            <TableCell>{u.role}</TableCell>
                            <TableCell>{u.active ? "ATIVO" : "INATIVADO"}</TableCell>
                            <TableCell>
                                [ <RouterLink to={`/admin/usuarios/${u.id}`}>Editar</RouterLink> ]
                            </TableCell>
                            <TableCell>
                                <Button variant="outlined" color="error" onClick={() => excluir(u)}>
                                    Excluir
                                </Button>
                            </TableCell>
                        </TableRow>
                        )
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}
