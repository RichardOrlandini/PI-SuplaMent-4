import { FiArrowLeft, FiUser, FiMail, FiLock, FiCamera } from "react-icons/fi"
import { useNavigate } from "react-router-dom";
import avatarPlaceholder from "../../../assets/avatarPlaceholder.svg";

import {
    Button, Typography, TextField,
} from "@mui/material";


import { Container, Form, Avatar } from "./style";
import { useState } from "react";
import { api } from "../../../services/api";
import autenticaStore from "common/stores/authentica.store";

export function Profile() {
    const user = autenticaStore.user;

    const [nome, setNome] = useState(user?.nome);
    const [senha, setSenha] = useState("");
    const [novaSenha, setNovaSenha] = useState("");

    const avatarUrl = user?.avatar ? `${api.defaults.baseURL}/files/${user.avatar}` : avatarPlaceholder;

    const [avatar, setAvatar] = useState(avatarUrl);
    const [avatarFile, setAvatarFile] = useState<File | null>(null);

    const navigate = useNavigate();

    function handleBack() {
        navigate(-1)
    }

    async function handleUpdate() {
        const updated = {
            nome,
            senha,
        }
    }


    async function handleChangeAvatar(event: React.ChangeEvent<HTMLInputElement>) {
        if (event.target.files && event.target.files[0]) {
            const file = event.target.files[0];
            setAvatarFile(file);
    
            const imagePreview = URL.createObjectURL(file);
            setAvatar(imagePreview);
        }
    }
    

    return (
        <Container  backgroundColor="#3E3B47">
            <header>
                <button
                    type="button"
                    onClick={handleBack}
                >

                    <FiArrowLeft size={24} />
                </button>
            </header>

            <Form>
                <Avatar>
                    <img
                        src={avatar} alt="Foto do usúario" />
                    <label
                        htmlFor="avatar">
                        <FiCamera />
                        <input
                            id="avatar"
                            type="file"
                            onChange={handleChangeAvatar}
                        />
                    </label>
                </Avatar>

                <TextField
                    value={nome}
                    onChange={e => setNome(e.target.value)}
                    label="Nome do Usuário"
                    variant="standard"
                    fullWidth
                    required
                />

                <TextField
                    value={senha}
                    onChange={e => setSenha(e.target.value)}
                    label="Senha atual"
                    variant="standard"
                    fullWidth
                    required
                />


                <TextField
                    value={novaSenha}
                    onChange={e => setNovaSenha(e.target.value)}
                    label="Nova senha"
                    variant="standard"
                    fullWidth
                    required
                />
                <Button title="Salvar" onClick={handleUpdate} />
            </Form>

        </Container>
    )
}