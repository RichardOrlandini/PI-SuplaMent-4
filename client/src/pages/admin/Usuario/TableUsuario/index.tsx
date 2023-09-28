
import {
    Button, Paper, Table, TableBody, TableCell, Typography, TextField,
    TableContainer, TableHead, TableRow, Box, FormControl, InputLabel, Select, MenuItem,
    Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText
} from "@mui/material";
import { useEffect, useState } from "react"
import { api } from "../../../../services/api";
import { Link as RouterLink, useNavigate } from 'react-router-dom'
import { IPaginacao } from "../../../../shared/interfaces/IPaginacao";
import { IUsuario } from "../../../../shared/interfaces/IUsuario";
import { useAuth } from "../../../../hooks/auth";
import { IParametrosBusca } from "../../../../shared/interfaces/IParametrosBusca";
import { AxiosRequestConfig } from "axios";

export function TableUsuario() {

    const [usuarios, setUsuarios] = useState<IUsuario[]>([]);
    const [ativo, setAtivo] = useState(true);
    const [openConfirmationDialog, setOpenConfirmationDialog] = useState(false);
    const [selectedUserId, setSelectedUserId] = useState(0);
    const [proximaPagina, setProximaPagina] = useState('');
    const [paginaAnterior, setPaginaAnterior] = useState('');
    const [busca, setBusca] = useState('');
    const context = useAuth();
    const navigate = useNavigate();

    const carregarDados = (url: string, opcoes: AxiosRequestConfig = {}) => {
        const token = localStorage.getItem("@suplament:token")
        api.get<IPaginacao<IUsuario>>(url, {
            ...opcoes,
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then(resp => {
                setUsuarios(resp.data.content);
                setProximaPagina(resp.data.last ? '' : `/usuarios?page=${resp.data.number + 1}`);
                setPaginaAnterior(resp.data.first ? '' : `/usuarios?page=${resp.data.number - 1}`);
            })
            .catch(e => {
                if (e.message && e.code === "ERR_BAD_REQUEST") {
                    alert(`Token de acesso inspirado, faça login novamente`)
                    context?.signOut();
                    navigate("/");
                }
            })
    }

    const excluir = ( id: number) => {
        const token = localStorage.getItem("@suplament:token");

        let status = ativo;

        if (ativo === null || ativo === undefined) {
            status = false;
        }
         api.put(`/usuarios/${id}/status`)
            .then(() => {
                carregarDados("/usuarios");
            })
            .catch(error => {
                console.log(error);
            });
    }

    const confirmarAtualizacaoStatus = () => {
        setOpenConfirmationDialog(false);
        excluir(selectedUserId)
    }

    const atualizarStatus = () => {
        setOpenConfirmationDialog(true);
    }

    const cancelarAtualizacaoStatus = () => {
        setOpenConfirmationDialog(false);
    }



    useEffect(() => {
        carregarDados("/usuarios")
    }, []);




    const buscar = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (!busca) {
            alert("Digite o nome de um produto");
            return;
        }

        const opcoes = {
            params: {

            } as IParametrosBusca
        }

        if (busca) {
            opcoes.params.nome = busca;
        }
        carregarDados('/usuarios/busca', opcoes)
    }

    return (
        <TableContainer component={Paper} >

            <Box sx={{ display: 'flex', alignItems: 'center', width: '800px' }}>
                <RouterLink to="novo">
                    <Button sx={{ backgroundColor: '#666360', color: '#F4EDE8', marginLeft: 5, marginTop: 2, flex: '0 0 50%' }}>
                        Novo +
                    </Button>
                </RouterLink>

                <form onSubmit={buscar} style={{ display: 'flex', flex: '0 0 50%', marginLeft: '60px' }}>
                    <TextField
                        value={busca}
                        onChange={evento => setBusca(evento.target.value)}
                        label="Digite o nome do Usuário"
                        placeholder="Digite o nome do Usuário"
                        variant="standard"
                        type="text"
                        required
                        style={{ flex: '1' }}
                    />
                    <Button type='submit'>buscar</Button>
                </form>
            </Box>

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
                                <Typography component="h6" sx={{ marginLeft: 4 }} variant="h6">{u.ativo ? "Ativo" : "Inativo"}</Typography>

                                <Button
                                    variant="outlined"
                                    color="primary"
                                    onClick={() => {
                                        atualizarStatus();
                                        setAtivo(u.ativo)
                                        setSelectedUserId(u.id);
                                    }}
                                >
                                    Atualizar
                                </Button>
                            </TableCell>

                            <TableCell>
                                [ <RouterLink to={`/admin/usuarios/${u.id}`}>Editar</RouterLink> ]
                            </TableCell>
                            <TableCell>
                                <Button variant="outlined" color="error" onClick={() => excluir(u.id)}>
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

                <FormControl margin="dense" fullWidth >
                    <InputLabel id="select-atv">Status</InputLabel>
                    <Select
                        labelId="select-atv"
                        required
                        variant="standard"
                        color="primary"
                        value={ativo ? "Ativo" : "Inativo"}
                        onChange={e => {
                            if (e.target.value === "Ativo") {
                                setAtivo(true);
                            } else {
                                setAtivo(false);
                            }
                        }}>
                        <MenuItem value="Ativo">Ativo</MenuItem>
                        <MenuItem value="Inativo">Inativo</MenuItem>
                    </Select>
                </FormControl>

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
