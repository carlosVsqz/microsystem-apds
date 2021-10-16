import { errorHandler } from 'helpers/api/error-handler';
import { jwtMiddleware} from 'helpers/api/jwt-middleware';

export { apiHandler };

function apiHandler(handler) {
    return async (req, res) => {
        try {
            // global middleware
            await jwtMiddleware(req, res);

            // route handler
            await handler(req, res);
        } catch (err) {
            // global error handler
            errorHandler(err, res);
        }
    }
}