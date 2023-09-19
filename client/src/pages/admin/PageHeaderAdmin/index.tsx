import { Box, Button, Typography, AppBar, Container, Toolbar, Link, Paper } from "@mui/material"
import { Link as RouterLink, Outlet } from 'react-router-dom'
import { Header } from "../../../components/Header"


export function PageHeaderAdmin() {

    return (
        <>
            <Header/>
            <AppBar position="static">

                <Container maxWidth="xl">
                    <Toolbar>
                        <Typography variant="h6" sx={{ marginLeft: 10 }}>
                            Supla ADM
                        </Typography>

                        <Box sx={{ display: 'flex', flexGrow: 1 }}>

                            <Link component={RouterLink} to="/admin/categorias">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Categorias
                                </Button>
                            </Link>

                            <Link component={RouterLink} to="/admin/categorias/novo">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Nova Categoria
                                </Button>
                            </Link>

                            <Link component={RouterLink} to="/admin/produtos">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Produtos
                                </Button>
                            </Link>

                            <Link component={RouterLink} to="/admin/produtos/novo">
                                <Button sx={{ my: 2, color: 'white' }}>
                                    Novo Produto
                                </Button>
                            </Link>

                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
            <Box>
                <Container maxWidth="lg" sx={{ mt: 1 }}>
                    <Paper sx={{ p: 2 }}>
                        <Outlet />
                    </Paper>
                </Container>
            </Box>
        </>
    )
}
