// Next.js API route support: https://nextjs.org/docs/a pi-routes/introduction
import type {NextApiRequest, NextApiResponse} from 'next'

type Data = {
    ip: string | string[] | undefined
    ua: string | undefined
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<Data>
) {
    res.status(200).json({
        ip: req.headers["X-Forwarded-For"],
        ua: req.headers["user-agent"]
    })
}
