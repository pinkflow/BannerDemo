import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {Theme} from "@mui/material";
import LoginService from "../service/LoginService";

export interface LoginPageProps {
    setLoggedIn: (value: boolean) => void;
}

const useStyles = makeStyles((theme: Theme) => ({
    root: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        padding: theme.spacing(2),

        '& .MuiTextField-root': {
            margin: theme.spacing(1),
            width: '300px',
        },
        '& .MuiButtonBase-root': {
            margin: theme.spacing(2),
        },
    },
}));

const LoginPage = ({setLoggedIn}: LoginPageProps) => {
    const classes = useStyles();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = () => {
        LoginService.login(username, password).then(() => {
                setLoggedIn(true);
            }
        )
    };

    return (
        <form className={classes.root}>
            <TextField
                label="First Name"
                variant="filled"
                required
                value={username}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setUsername(e.target.value)}
            />
            <TextField
                label="Password"
                variant="filled"
                type="password"
                required
                value={password}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) => setPassword(e.target.value)}
            />
            <div>
                <Button variant="contained" color="primary" onClick={handleSubmit}>
                    sign up
                </Button>
            </div>
        </form>
    );
};

export default LoginPage;