import { Handler } from '@netlify/functions';
import crypto from 'crypto';

function verifySignature(rawBody: string, signature: string | undefined, secret: string) {
  const hmac = crypto.createHmac('sha256', secret);
  hmac.update(rawBody);
  const expected = hmac.digest('base64');

  // Fast fail if header is missing or lengths differ; avoids RangeError
  if (!signature || signature.length !== expected.length) {
    return false;
  }

  return crypto.timingSafeEqual(Buffer.from(signature), Buffer.from(expected));
}

export const handler: Handler = async (event) => {
  if (event.httpMethod !== 'POST') {
    return { statusCode: 405, body: 'Method Not Allowed' };
  }

  const signature = event.headers['x-signature'] as string || '';
  const secret = process.env.WEBHOOK_SECRET || '';
  if (!verifySignature(event.body || '', signature, secret)) {
    return { statusCode: 403, body: 'Invalid signature' };
  }

  console.log('Webhook payload:', event.body);
  const data = JSON.parse(event.body || '{}');
  const response = {
    ...data,
    text: data.eventType === 'request' ? 'Hello, how are you?' : 'I am doing well, thank you for asking.',
    saveModified: data.eventType === 'request'
  };

  return {
    statusCode: 200,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(response)
  };
};
