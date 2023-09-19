
import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material"
import { useEffect, useState } from "react"
import { IProduto } from "../../../shared/interfaces/IProduto"
import { api } from "../../../services/api";
import { Link as RouterLink } from 'react-router-dom'
import { useAuth } from "../../../hooks/auth";
import { IPaginacao } from "../../../shared/interfaces/IPaginacao";

export function FormProduto() {

    const [produtos, setProdutos] = useState<IProduto[]>([]);

    useEffect(() => {

        api.get<IPaginacao<IProduto>>("/produtos")
            .then(resp => {
                setProdutos(resp.data.content);
            });
    }, []);

    const excluir = (produtoAhSerExcluido: IProduto) => {
        api.delete(`/produtos/${produtoAhSerExcluido.id}`,)
            .then(() => {
                const produtosFiltrados = produtos.filter(produto => produto.id !== produtoAhSerExcluido.id);
                setProdutos([...produtosFiltrados])
            });
    }

    return (
        <TableContainer component={Paper} >

            <Table>

                <TableHead>
                    <TableRow>
                        <TableCell>Nome</TableCell>
                        <TableCell>Quantidade</TableCell>
                        <TableCell>Valor</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Editar</TableCell>
                        <TableCell>Excluir</TableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {
                        produtos.map(p => <TableRow key={p.id}>
                            <TableCell>{p.nome}</TableCell>
                            <TableCell>{p.qtd}</TableCell>
                            <TableCell>{p.valor}</TableCell>
                            <TableCell>{p.ativo ? "ATIVO" : "INATIVADO"}</TableCell>
                            <TableCell>
                                [ <RouterLink to={`/admin/produtos/${p.id}`}>Editar</RouterLink> ]
                            </TableCell>
                            <TableCell>
                                <Button variant="outlined" color="error" onClick={() => excluir(p)}>
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