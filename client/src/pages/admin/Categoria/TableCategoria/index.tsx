import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"

import { Link as RouterLink } from 'react-router-dom'
import ICategoria from "../../../../shared/interfaces/ICategoria"
import { api } from "../../../../services/api";

export function TableCategoria() {
    const [categorias, setCategorias] = useState<ICategoria[]>([]);

    useEffect(() => {
        api.get<ICategoria[]>("/categorias")
            .then(resp => setCategorias(resp.data))
    }, []);

    const excluir = (categoriaAhSerExcluida: ICategoria) => {
        api.delete(`/categorias/${categoriaAhSerExcluida.id}`)
            .then(() => {
                const categoriasFiltradas = categorias.filter(categoria => categoria.id !== categoriaAhSerExcluida.id);
                setCategorias([...categoriasFiltradas]);
            });
    }

    return (
        <TableContainer component={Paper}>

<RouterLink  to="/novo">
                <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2 }}>
                    Novo
                </Button>
            </RouterLink>

            <Table sx={{marginTop: 5}}>

                <TableHead>
                    <TableRow>
                        <TableCell>Nome</TableCell>
                        <TableCell>Editar</TableCell>
                        <TableCell>Excluir</TableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {categorias.map(c =>
                        <TableRow key={c.id}>

                            <TableCell>{c.nome}</TableCell>
                            <TableCell>
                                [ <RouterLink to={`/admin/categorias/${c.id}`}>Editar</RouterLink> ]
                            </TableCell>
                            <TableCell>
                                <Button variant="outlined" color="error" onClick={() => excluir(c)}>
                                    Excluir
                                </Button>
                            </TableCell>

                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </TableContainer>
    )
}
