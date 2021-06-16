import type { InitializerData } from "../dataTypes";

export const getInitialData = async (): Promise<InitializerData> => {
    const response = await fetch("http://localhost:8080/");
    if (!response.ok) {
        throw new Error('Bad response');
    }
	return await response.json();
}
