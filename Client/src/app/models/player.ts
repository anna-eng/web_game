import { PlayerTypes } from './playerTypes';

export class Player {
    id?: String;
    name: String;
    role:PlayerTypes;

    constructor(name) {
        this.name = name;
    }
}