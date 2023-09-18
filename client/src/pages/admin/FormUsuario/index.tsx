
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import { api } from "../../../services/api";
import { Link as RouterLink } from 'react-router-dom'
import { IPaginacao } from "../../../shared/interfaces/IPaginacao";
import { IUsuario } from "../../../shared/interfaces/IUsuario";

export function FormUsuario() {

    const [usuarios, setUsuarios] = useState<IUsuario[]>([]);

    useEffect(() => {

        api.get<IPaginacao<IUsuario>>("/usuarios")
            .then(resp => {
                setUsuarios(resp.data.content);
            });
    }, []);

    const excluir = (usuarioAhSerExcluido: IUsuario) => {
        api.delete(`/usuarios/${usuarioAhSerExcluido.id}`,)
            .then(() => {
                const usuariosFiltrados = usuarios.filter(usuario => usuario.id !== usuarioAhSerExcluido.id);
                setUsuarios([...usuariosFiltrados])
            });
    }

    return (
        <TableContainer component={Paper} >

            <Table>

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
