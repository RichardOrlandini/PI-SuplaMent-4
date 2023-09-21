
import {
    Button, Paper, Table, TableBody, TableCell, Typography,
    TableContainer, TableHead, TableRow, Box, InputLabel,
    Select, MenuItem, Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText
} from "@mui/material"
import { useEffect, useState } from "react"
import { api } from "../../../../services/api";
import { Link as RouterLink } from 'react-router-dom'
import { IPaginacao } from "../../../../shared/interfaces/IPaginacao";
import { IUsuario } from "../../../../shared/interfaces/IUsuario";

export function TableUsuario() {

    const [usuarios, setUsuarios] = useState<IUsuario[]>([]);
    const [ativo, setAtivo] = useState(true);
    const [openConfirmationDialog, setOpenConfirmationDialog] = useState(false);
    const [selectedUserId, setSelectedUserId] = useState(0);
    const [proximaPagina, setProximaPagina] = useState('');
    const [paginaAnterior, setPaginaAnterior] = useState('');

    const carregarDados = (url: string) => {
        const token = localStorage.getItem("@suplament:token")
        console.log(token)
        api.get<IPaginacao<IUsuario>>(url, {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(resp => {
                setUsuarios(resp.data.content);
                setProximaPagina(resp.data.last ? '' : `/usuarios?page=${resp.data.number + 1}`);
                setPaginaAnterior(resp.data.first ? '' : `/usuarios?page=${resp.data.number - 1}`);
            });
    }

    const atualizarStatus = () => {
        setOpenConfirmationDialog(true);
    }

    const cancelarAtualizacaoStatus = () => {
        setOpenConfirmationDialog(false);
    }

    const confirmarAtualizacaoStatus = () => {
        setOpenConfirmationDialog(false);

        api.put(`/usuarios/${selectedUserId}/status`, { ativo })
            .then(() => {
                carregarDados("/usuarios");
            })
            .catch(error => {
                console.log(error);
            });
    }

    useEffect(() => {
        carregarDados("/usuarios")
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
            <RouterLink to="novo">
                <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2 }}>
                    Novo
                </Button>
            </RouterLink>

            <Table sx={{ marginTop: 5 }}>

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
                            <TableCell  >{u.role}</TableCell>
                            <TableCell sx={{ width: 6 }} >
                            <Typography component="h6" sx={{marginLeft:4}} variant="h6">{u.ativo ? "Ativo" : "Inativo"}</Typography>

                                <Button
                                    variant="outlined"
                                    color="primary"
                                    onClick={ () => {
                                        atualizarStatus();
                                        setAtivo(u.ativo)
                                        setSelectedUserId(u.id);
                                    } }
                                >
                                    Atualizar
                                </Button>
                            </TableCell>

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

            <Dialog
                open={openConfirmationDialog}
                onClose={cancelarAtualizacaoStatus}
            >
                <DialogTitle>Confirmação</DialogTitle>

                <Typography>Status Atual</Typography>
                <Select
                    required
                    value={ativo ? "Ativo" : "Inativo"}
                    onChange={(e) => {
                        setAtivo(e.target.value === "A" ? true : false);
                    }}
                    variant="standard"
                    color="primary"
                >
                    <MenuItem value={"A"}>Ativo</MenuItem>
                    <MenuItem value={"B"}>Inativo</MenuItem>
                </Select>

                <DialogContent>
                    <DialogContentText>
                        Tem certeza que deseja atualizar o status?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={cancelarAtualizacaoStatus} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={confirmarAtualizacaoStatus} color="primary">
                        Confirmar
                    </Button>
                </DialogActions>
            </Dialog>

            <Box sx={{ marginTop: 2, marginLeft: 50, marginRight: 50 }}>
                {<Button onClick={() => carregarDados(paginaAnterior)} disabled={!paginaAnterior}>
                    Página Anterior
                </Button>}

                {<Button onClick={() => carregarDados(proximaPagina)} disabled={!proximaPagina}>
                    Próxima página
                </Button>}
            </Box>

        </TableContainer>
    )
}
